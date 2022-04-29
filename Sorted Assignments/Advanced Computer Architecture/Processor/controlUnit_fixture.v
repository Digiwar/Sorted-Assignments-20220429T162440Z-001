`include "controlUnit.v"


module controlUnit_fixture;


wire branch,jump,writeEN1,writeEN2,ifIDFlush,
Op1Mux,Op2Mux,dataMemWR,
dirALUMux,wbMUX,dataMemRD; 
reg [7:0] ctrlSignal;
wire [3:0] aLUControl; 





controlUnit testCTRLSig(
ctrlSignal,branch,jump,writeEN1,writeEN2,
aLUControl,ifIDFlush,Op1Mux,Op2Mux,
dataMemWR,dirALUMux,wbMUX,dataMemRD);



initial
begin
#1;$monitor("Instruction: = %b",ctrlSignal,
" branch = %b",branch,
" jump = %b",jump,
" writeEN1 / 2 = %b",writeEN1,writeEN2,
" aLUControl  = %b",aLUControl,
" ifIDFlush = %b",ifIDFlush,
" Op1/2Mux = %b",Op1Mux,Op2Mux,
" dataMemWR/RD = %b",dataMemWR,dataMemRD,
" dirALUMux = %b",dirALUMux,
" wbMUX = %b", wbMUX);
end

initial
begin
#1; ctrlSignal = 8'b00000000;
#1; ctrlSignal = 8'b00000001;
#1; ctrlSignal = 8'b00000100;
#1; ctrlSignal = 8'b00001000;
#1; ctrlSignal = 8'b00001110;
#1; ctrlSignal = 8'b00001111;
#1; ctrlSignal = 8'b00010000;
#1; ctrlSignal = 8'b00100000;
#1; ctrlSignal = 8'b10000000;
#1; ctrlSignal = 8'b10010000;
#1; ctrlSignal = 8'b10100000;
#1; ctrlSignal = 8'b10110000;
#1; ctrlSignal = 8'b01000000;
#1; ctrlSignal = 8'b01010000;
#1; ctrlSignal = 8'b01100000;
#1; ctrlSignal = 8'b11000000;
#1; ctrlSignal = 8'b11110000;
#1; $finish;
end



endmodule
 
