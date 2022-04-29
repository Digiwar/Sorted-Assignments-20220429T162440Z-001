// k-lib.c, 159

#include "k-include.h"
#include "k-type.h"
#include "k-data.h"

// clear DRAM data block, zero-fill it
void Bzero(char *p, int bytes) {
	while(bytes--)*p++ = '\0';
}

int QisEmpty(q_t *p) { // return 1 if empty, else 0
   return(p->tail == 0);
}

int QisFull(q_t *p) { // return 1 if full, else 0
   return(p->tail== Q_SIZE);
}

// dequeue, 1st # in queue; if queue empty, return -1
// move rest to front by a notch, set empty spaces -1
int DeQ(q_t *p) { // return -1 if q[] is empty
   int i,to_return;

   if(QisEmpty(p)) {
      cons_printf("Panic:Queue Empty");
      return NONE;
   }
   to_return=p->q[0];
   p->tail--;
   for(i=0;i<p->tail;i++)p->q[i]=p->q[i+1];
   for(i=p->tail;i<Q_SIZE;i++) p->q[i]=NONE;
   return to_return;
}

// if not full, enqueue # to tail slot in queue
void EnQ(int to_add, q_t *p) {
   if(QisFull(p)) {
      cons_printf("Panic: queue is full, cannot EnQ!\n");
      return;
   }
   p->q[p->tail]=to_add;
   p->tail++;
}

