// k-sr.h, 159

#ifndef _K_SR__
#define _K_SR__

#include "k-include.h" 
#include "k-type.h" 
#include "k-data.h" 
#include "k-lib.h" 
#include "k-sr.h" 

void NewProcSR(func_p_t);
void TimerSR(void);
int GetPidSR(void);
void CheckWakeProc(void);
void ShowCharCallSR(int,int,char);
void SleepSR(int);
int MuxCreateSR(int);
void MuxOpSR(int,int);
void TermSR(int);
void TermTxSR(int);
void TermRxSr(int);
#endif
