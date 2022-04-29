// k-type.h, 159

#ifndef __K_TYPE__
#define __K_TYPE__

#include "k-const.h"

typedef void (*func_p_t)(void); // void-return function pointer type

typedef enum {UNUSED, READY, RUN, SLEEP, SUSPEND} state_t;

typedef struct {
   
   unsigned int esi, edi, ebp, esp , ebx, edx, ecx, eax, entry_id;
   unsigned int eip;
   unsigned int cs;
   unsigned int efl;
} trapframe_t;

typedef struct {
   state_t state;
   int run_count;
   int total_count;
   trapframe_t *trapframe_p;
   int wake_centi_sec;                 
} pcb_t;                     

typedef struct {             // generic queue type
  int q[Q_SIZE];                        // for a simple queue
  int tail;
} q_t;

typedef struct{
   int flag;
   int creater;
   q_t suspend_q;
} mux_t;

typedef struct {
	int tx_missed,  //when initialized or after output last char
		io_base,
		out_mux;
	q_t out_q;
	//new
	q_t in_q; 
	q_t echo_q;
	int in_mux;
} term_t;


#endif
