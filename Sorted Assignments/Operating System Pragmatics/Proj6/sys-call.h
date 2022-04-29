// sys-call.h

#ifndef __SYS_CALL__
#define __SYS_CALL__


#include "k-const.h"

int GetPidCall(void);

void ShowCharCall(int,int,char);

void SleepCall(int);

int MuxCreateCall(int);

void MuxOpCall(int,int);

void WriteCall(int,char *);

#endif
