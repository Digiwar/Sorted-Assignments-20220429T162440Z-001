// sys-call.c
// used for kernel services

#include "k-lib.h"
#include "k-const.h"
#include "k-data.h"
int GetPidCall(void){
   int pid;

   asm("int %1;
         movl %%eax, %0"
       :"=g"(pid)
       :"g" (GETPID_CALL)
       : "eax"
   );

   return pid;

}

void ShowCharCall(int row,int col,char ch){
   asm("movl %0, %%eax;
	movl %1, %%ebx;
	movl %2, %%ecx;
	int %3"
	: 
	: "g" (row),"g" (col),"g" (ch),"g" (SHOWCHAR_CALL)
	: "eax", "ebx", "ecx"
   );

}

void SleepCall(int centi_sec){
    asm("movl %0,%%eax;
         int %1"
         :
         :"g" (centi_sec),"g" (SLEEP_CALL)
         :"eax"
         );
    //for(int i=0,i<centi_sec,i++)asm("inb $0x80");


}

int MuxCreateCall(int flag){
   int mux_id;
   asm("movl %1, %%eax;
	int %2;
	movl %%eax, %0"
	:"=g"(mux_id)
	:"g"(flag),"g"(MUX_CREATE_CALL)
	:"eax"
   );
   return mux_id;
}

void MuxOpCall(int mux_id, int opcode){
   asm("movl %0, %%eax;
	movl %1, %%ebx
        int %2"
	:
	:"g"(mux_id),"g"(opcode),"g"(MUX_OP_CALL)
	:"eax", "ebx"
   );
}

void WriteCall(int device, char *str){
  int temp_row= GetPidCall();  
  int temp_col=0;
  int term_no= 0;
  if(device == STDOUT){
     while(*str != '\0'){
	ShowCharCall(temp_row,temp_col,*str);
	str++;
	temp_col++;
	}
     }else {
	if (device == TERM0_INTR){
	
		term_no = 0; 
	}else {
		term_no = 1;
	}
	while (*str != '\0'){
		MuxOpCall(term[term_no].out_mux, LOCK);
		EnQ((int)*str,&term[term_no].out_q);//enqueue string
		if(device == TERM0_INTR) { 
			asm("int %0"
			     :		
			     :"g" (TERM0_INTR)	
				);
	      }	else{
			asm("int %0"
			     : 
			     : "g" (TERM1_INTR)	
				);
		}
		str++;
	
     }	
  }
}
void ReadCall(int device, char *str){ 
	int term_no= 0;
	int char_count = 0;
	char temp;
	if (device == TERM0_INTR){
		term_no= 0;
	}else{
		term_no = 1;
	}
	while(1){
		MuxOpCall(term[term_no].in_mux, LOCK);
		temp = DeQ(&term[term_no].in_q);
		*str = &temp;
		if (temp == '\0'){ 
		return;	
		}
		*str++;
		char_count++;
		if (char_count == STR_SIZE ){ // at last avaible spot of string I am not sure how to check for the last available spot of string how do we know string size with a pointer and q
	//STR_SIZE is defined in the k-const file which terms if the string is appropriate for being read	
		*str = '\0';
		return;
		}
	}	
}
