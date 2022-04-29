// k-sr.c, 159

#include "k-include.h"
#include "k-type.h"
#include "k-data.h"
#include "k-lib.h"
#include "k-sr.h"
#include "k-const.h"

// to create a process: alloc PID, PCB, and process stack
// build trapframe, initialize PCB, record PID to ready_q
void NewProcSR(func_p_t p) {  // arg: where process code starts
   int pid;

   if(QisEmpty(&pid_q)) {     // may occur if too many been created
      cons_printf("Panic: no more process!\n");
      return;                    // cannot continue, alternative: breakpoint();
   }

   pid = DeQ(&pid_q);                                       // alloc PID (1st is 0)
   Bzero((char *)&pcb[pid], sizeof(pcb_t));                          // clear PCB
   Bzero((char *)&proc_stack[pid][0],PROC_STACK_SIZE);                                       // clear stack
   pcb[pid].state=READY;                                       // change process state

   if(pid > 0) EnQ(pid,&ready_q);                           // queue to ready_q if > 0

// point trapframe_p to stack & fill it out
   //pcb[pid].trapframe_p=(trapframe_t *) &proc_stack[pid][PROC_STACK_SIZE-sizeof(trapframe_t)];                // point to most recent area on the stack
   pcb[pid].trapframe_p=(trapframe_t *)&proc_stack[pid][PROC_STACK_SIZE];
   pcb[pid].trapframe_p--;
   pcb[pid].trapframe_p->efl = EF_DEFAULT_VALUE|EF_INTR; // enables intr
   pcb[pid].trapframe_p->cs = get_cs();                  // dupl from CPU
   pcb[pid].trapframe_p->eip =(int) p;                          // set to code
}

// count run_count and switch if hitting time slice
void TimerSR(void) {
   outportb(PIC_CONTROL,TIMER_DONE);                              // notify PIC timer done
   
   sys_centi_sec++;
   CheckWakeProc();
   if(run_pid == 0){
   return; 
   }
   pcb[run_pid].run_count++;                                       // count up run_count
   pcb[run_pid].total_count++;                                       // count up total_count

   if(pcb[run_pid].run_count==TIME_SLICE) {       // if runs long enough
      EnQ(run_pid, &ready_q);                                    // move it to ready_q
      pcb[run_pid].state = READY;                                    // change its state
      run_pid = NONE;                                         // running proc = NONE
   }
}

int GetPidSR(void){
	return run_pid;
}

void SleepSR(int centi_sec){
	pcb[run_pid].wake_centi_sec = sys_centi_sec + centi_sec; //take current time add wait time and set that integer to wake centi sec
	pcb[run_pid].state = SLEEP;
	EnQ(run_pid, &sleep_q);
	run_pid = NONE;	
}

void ShowCharCallSR(int row, int col, char ch) {
   unsigned short *p = VID_HOME;  // upper-left corner of display
   p+= row*80;
   p+= col;
   *p= ch+VID_MASK;
} 

void CheckWakeProc(){
	int temp_PID;
	int temp_tail;
	
	int i;
	temp_tail = sleep_q.tail;
	for(i =0;i<temp_tail;i++){
		
		temp_PID = DeQ(&sleep_q);
		if(pcb[temp_PID].wake_centi_sec <= sys_centi_sec){
			
			pcb[temp_PID].state = READY;		//Change the status of the pcb to ready
			EnQ(temp_PID, &ready_q);	//Enque to ready queue		
		}else{
			EnQ(temp_PID,&sleep_q);		//If not ready requeue to sleep_q
		}
	}
}

int MuxCreateSR(int flag){
	int mux_id = DeQ(&mux_q);
	Bzero((char *)&mux[mux_id].suspend_q,sizeof(q_t));
	mux[mux_id].flag = flag;
	mux[mux_id].creater = run_pid;
	
	return mux_id;
	
	
	
}

void  MuxOpSR(int mux_id, int opcode){
	int temp_pid;
	if(opcode == LOCK){
		if(mux[mux_id].flag > 0){
			mux[mux_id].flag--;
		}else{
			EnQ(run_pid,&mux[mux_id].suspend_q);
			pcb[run_pid].state = SUSPEND;
			run_pid=NONE;
		}
	}else if(opcode == UNLOCK){
		if(QisEmpty(&mux[mux_id].suspend_q))	
			mux[mux_id].flag++;
		else{
			temp_pid=DeQ(&mux[mux_id].suspend_q);
			EnQ(temp_pid,&ready_q);
			pcb[temp_pid].state=READY;
		}
	}
	return;
}

void TermSR(int term_no) {
	int type_event;
	type_event = inportb(term[term_no].io_base+IIR); // read the type event from IIR of the terminal port
	// (IIR is Interupt INdicator Reg)
	if (type_event == TXRDY){ 
		TermTxSR(term_no);// if its TXRDY call TermTxSR(term_n)
	} else{
		TermRxSR(term_no); // else if it is RXRDY, call TermRxSR(term_no) 
	}
	if (term[term_no].tx_missed == TRUE){
		TermTxSR(term_no);
	} 
	// if the tx_missed flag is true, also call Term TxSr(term_no)
	
}
void  TermTxSR(int term_no){
	char  c;
	if (QisEmpty(&term[term_no].out_q) && QisEmpty(&term[term_no].echo_q)){  //if out q is empty
	term[term_no].tx_missed = TRUE;
	return;
	}else{	
	c = (char *) DeQ(&term[term_no].out_q); //get 1st char from out_q
	outportb(term[term_no].io_base + DATA, c);//use outportb() to send it to the DATA register of the terminal port
	term[term_no].tx_missed = FALSE;//set the tx missed flag to FALSE 
	MuxOpSR(term[term_no].out_mux, UNLOCK); //unlock the out_mux of the terminal interfacwe data strucutre
 	}
}

void TermRxSR(term_no){
	// read a char from teh terminal io_base + DATA
	char temp_ch;
	temp_ch = inportb(term[term_no].io_base+DATA);
	// enqueue char to the terminal echo_q
	EnQ(temp_ch, &term[term_no].echo_q);
	
	// if char is CR -> also enqueue NUL to the terminal in_q
	if(temp_ch == '\r'){
		EnQ('\0', &term[term_no].in_q);
	}else{
	// else -> enqueue char to the terminal in_q
		EnQ(temp_ch, &term[term_no].in_q);
	}
	MuxOpSR(term[term_no].in_mux, UNLOCK);
	// unlock the terminal in_mux
	return;
}
