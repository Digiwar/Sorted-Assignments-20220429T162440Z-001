// k-lib.h, 159

#ifndef __K_LIB__
#define __K_LIB__

#include "k-include.h"
#include "k-type.h"
#include "k-data.h"

int QisEmpty(q_t *);
int QisFull(q_t *);     // prototype those in k-lib.c here
void Bzero(char *,int);
void EnQ(int,q_t *);
int DeQ(q_t *);

#endif
