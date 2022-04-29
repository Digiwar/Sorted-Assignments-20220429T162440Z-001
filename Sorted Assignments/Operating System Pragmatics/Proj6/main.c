// main.c, 159
// OS phase 2
//
// Team Name: DirtyDs (Members: Daniel Komac(215180863),Dylan McNeely(216546591))

#include "k-include.h"  // SPEDE includes
#include "k-entry.h"    // entries to kernel (TimerEntry, etc.)
#include "k-type.h"     // kernel data types
#include "k-lib.h"      // small handy functions
#include "k-sr.h"       // kernel service routines
#include "proc.h"       // all user process code here

// kernel data are all here:
int run_pid;                        // current running PID; if -1, none selected
int sys_centi_sec;
int vid_mux;

mux_t mux[MUX_SIZE];
q_t pid_q,ready_q,sleep_q,mux_q;                 // avail PID and those created/ready to run


pcb_t pcb[PROC_SIZE];               // Process Control Blocks
char proc_stack[PROC_SIZE][PROC_STACK_SIZE];   // process runtime stacks
struct i386_gate *intr_table;    // intr table's DRAM location

term_t term[TERM_SIZE] = {{ TRUE, TERM0_IO_BASE }, { TRUE, TERM1_IO_BASE }};

void InitKernelData(void) {         // init kernel data
   int i;
   sys_centi_sec = 0; 
   intr_table  = get_idt_base();            // get intr table location

   Bzero((char *)&sleep_q,sizeof(q_t));
   Bzero((char *)&pid_q,sizeof(q_t));                      // clear 2 queues
   Bzero((char *)&ready_q,sizeof(q_t));
   Bzero((char *)&mux_q,sizeof(q_t));
   for(i=0;i<PROC_SIZE;i++){                        // put all PID's to pid queue
       EnQ(i, &pid_q);           
       EnQ(i, &mux_q);
   }
   run_pid=NONE;
   /*for(i=0;i<MUX_SIZE;i++){
	EnQ(i,&mux_q);	
   } */
}

void InitKernelControl(void) {      // init kernel control
   fill_gate(&intr_table[TIMER_INTR],(int)TimerEntry,get_cs(),ACC_INTR_GATE,0);                  // fill out intr table for timer
   fill_gate(&intr_table[GETPID_CALL],(int)GetPidEntry,get_cs(),ACC_INTR_GATE,0);	   
   fill_gate(&intr_table[SHOWCHAR_CALL],(int)ShowCharEntry,get_cs(),ACC_INTR_GATE,0);
   fill_gate(&intr_table[SLEEP_CALL],(int)SleepEntry,get_cs(),ACC_INTR_GATE,0);
   fill_gate(&intr_table[MUX_CREATE_CALL],(int)MuxCreateEntry,get_cs(),ACC_INTR_GATE,0);
   fill_gate(&intr_table[MUX_OP_CALL],(int)MuxOpEntry,get_cs(),ACC_INTR_GATE,0);
   fill_gate(&intr_table[TERM0_INTR], (int)Term0Entry,get_cs(),ACC_INTR_GATE,0);
   fill_gate(&intr_table[TERM1_INTR], (int)Term1Entry,get_cs(),ACC_INTR_GATE,0);
   outportb(PIC_MASK,MASK);                   // mask out PIC for timer
}

void Scheduler(void) {      // choose run_pid
   if(run_pid>0) return; // OK/picked

   if(QisEmpty(&ready_q))
      run_pid=0;   // pick InitProc
   else{
      pcb[0].state = READY;
      run_pid = DeQ(&ready_q);
   }
   pcb[run_pid].run_count = 0;                    // reset run_count of selected proc
   pcb[run_pid].state = RUN;                    // upgrade its state to run
}

int main(void) {                          // OS bootstraps
   InitKernelData();                      //call to initialize kernel data
   InitKernelControl();                   //call to initialize kernel control

   NewProcSR(InitProc);// create InitProc
   Scheduler();
   Loader(pcb[run_pid].trapframe_p); // load/run it

   return 0; // statement never reached, compiler asks it for syntax
}

void Kernel(trapframe_t *trapframe_p) {           // kernel runs
   char ch;

   pcb[run_pid].trapframe_p = trapframe_p; // save it
   

   switch(trapframe_p->entry_id){
   	case TIMER_INTR:
		TimerSR();// handle timer intr
		break;
   	case SLEEP_CALL:
		SleepSR(trapframe_p->eax);
		break;
	case GETPID_CALL:
		trapframe_p->eax = GetPidSR();
		break;
	case SHOWCHAR_CALL:
		ShowCharCallSR(trapframe_p->eax, trapframe_p->ebx, trapframe_p->ecx);
		break;
	case MUX_CREATE_CALL:
		trapframe_p->eax = MuxCreateSR(trapframe_p->eax);
		break;
	case MUX_OP_CALL:
		MuxOpSR(trapframe_p->eax,trapframe_p->ebx);
		break;
	case TERM0_INTR:
		TermSR(0);
		outportb(PIC_CONTROL, TERM0_DONE);
		break;
	case TERM1_INTR:
		TermSR(1);
		outportb(PIC_CONTROL, TERM1_DONE);
		break;
 
		
   default:break;
   }

   if(cons_kbhit()) {            // check if keyboard pressed
      ch = cons_getchar();
      if(ch == 'b'){breakpoint();}                     // 'b' for breakpoint
                                // let's go to GDB
      else if(ch == 'n'){NewProcSR(UserProc);}                     // 'n' for new process
      // create a UserProc
   }
   Scheduler();    // may need to pick another proc
   Loader(pcb[run_pid].trapframe_p);
}

