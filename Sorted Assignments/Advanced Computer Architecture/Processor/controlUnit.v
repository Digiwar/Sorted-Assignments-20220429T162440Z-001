//Control unit


module controlUnit(ctrlSignal,branch,jump,writeEN1,writeEN2,aLUControl,ifIDFlush
,Op1Mux,Op2Mux,dataMemWR,dirALUMux,wbMUX,dataMemRead);


//I/O Declaration
input [7:0] ctrlSignal;

//Each individual output for each component required for each logic unit
output reg  branch,jump,writeEN1,writeEN2,ifIDFlush,Op1Mux
,Op2Mux,dirALUMux,wbMUX,dataMemRead,dataMemWR;
output reg [3:0] aLUControl;

 
always @ (*)
case(ctrlSignal)
	//Control Outputs for add instruction
	8'b00000000:
	begin
	branch = 0;
	jump = 0;
	writeEN1 = 1;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 4'b0010;
	dirALUMux = 0;
	wbMUX = 1;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b0000;
	end
	//

	//Control Outputs for sub instruction
	8'b00000001:
	begin
	branch = 0;
	jump = 0;
	writeEN1 = 1;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 4'b0010;
	dirALUMux = 0;
	wbMUX = 1;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b0001;
	end

	//

	//Control Outputs for mult instruction
	8'b00000100:
	begin
	branch = 0;
	jump = 0;
	writeEN1 = 1;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 4'b0010;
	dirALUMux = 0;
	wbMUX = 1;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b0010;
	end

	//

	//Control Outputs for div instruction
	8'b00001000:
	begin
	branch = 0;
	jump = 0;
	writeEN1 = 1;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 4'b0010;
	dirALUMux = 0;
	wbMUX = 1;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b0011;
	end

	//

	//Control Outputs for mov instruction
	8'b00001110:
	begin
	branch = 0;
	jump = 0;
	writeEN1 = 1;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 4'b0010;
	dirALUMux = 0;
	wbMUX = 1;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b0100;
	end

	//

	//Control Outputs for swap instruction
	8'b00001111:
	begin
	branch = 0;
	jump = 0;
	writeEN1 = 1;
	writeEN2 = 1;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 4'b0010;
	dirALUMux = 0;
	wbMUX = 1;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b0101;
	end

	//

	//Control Outputs for AND instruction
	8'b00010000:
	begin
	branch = 0;
	jump = 0;
	writeEN1 = 1;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 0;
	dirALUMux = 0;
	wbMUX = 1;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b0110;
	end
	//

	//Control Outputs for OR instruction
	8'b00100000:
	begin
	branch = 0;
	jump = 0;
	writeEN1 = 1;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 0;
	dirALUMux = 0;
	wbMUX = 1;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b0111;
	end
	//

	//Control Outputs for LOADByte instruction
	8'b10000000:
	begin
	branch = 0;
	jump = 0;
	writeEN1 = 1;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 4'b0000;
	Op2Mux = 4'b0010;
	dirALUMux = 1;
	wbMUX = 1;
	dataMemRead = 1;
	dataMemWR = 0;
	aLUControl = 4'b1000;
	end
	//

	//Control Outputs for STOREByte instruction
	8'b10010000:
	begin
	jump = 0;
	writeEN1 = 0;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 4'b0010;
	dirALUMux = 1;
	wbMUX = 0;
	dataMemRead = 0;
	dataMemWR = 1;
	aLUControl = 4'b1000;
	end
	//

	//Control Outputs for LOAD instruction
	8'b10100000:
	begin
	jump = 0;
	writeEN1 = 0;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 4'b0010;
	dirALUMux = 1;
	wbMUX = 1;
	dataMemRead = 1;
	dataMemWR = 0;
	aLUControl = 4'b1000;
	end
	//

	//Control Outputs for Store instruction
	8'b10110000:
	begin
	jump = 0;
	writeEN1 = 0;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 4'b0010;
	dirALUMux = 1;
	wbMUX = 0;
	dataMemRead = 0;
	dataMemWR = 1;
	aLUControl = 4'b1000;
	end
	//
	
	//Control for BLE instruction
	8'b01000000:
	begin
	branch = 1;
	jump = 0;
	writeEN1 = 0;
	writeEN2 = 0;
	ifIDFlush = 1;
	Op1Mux = 0;
	Op2Mux = 0;
	dirALUMux = 0;
	wbMUX = 0;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b1001;
	end
	//

	//Control for BGE instruction
	8'b01010000:
	begin
	branch = 1;
	jump = 0;
	writeEN1 = 0;
	writeEN2 = 0;
	ifIDFlush = 1;
	Op1Mux = 0;
	Op2Mux = 0;
	dirALUMux = 0;
	wbMUX = 0;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b1001;
	end
	//

	//Control for BE instruction
	8'b01100000:
	begin
	branch = 1;
	jump = 0;
	writeEN1 = 0;
	writeEN2 = 0;
	ifIDFlush = 1;
	Op1Mux = 0;
	Op2Mux = 0;
	dirALUMux = 0;
	wbMUX = 0;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b1001;
	end
	//

	//Control for JUMP instruction
	8'b11000000:
	begin
	branch = 0;
	jump = 1;
	writeEN1 = 0;
	writeEN2 = 0;
	ifIDFlush = 1;
	Op1Mux = 0;
	Op2Mux = 0;
	dirALUMux = 0;
	wbMUX = 0;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b1001;
	end
	//

	//Control for HALT instruction
	8'b11110000:
	begin
	jump = 0;
	writeEN1 = 0;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 0;
	dirALUMux = 0;
	wbMUX = 0;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b1001;
	end
	//
	
	default:
	begin
	jump = 0;
	writeEN1 = 0;
	writeEN2 = 0;
	ifIDFlush = 0;
	Op1Mux = 0;
	Op2Mux = 0;
	dirALUMux = 0;
	wbMUX = 0;
	dataMemRead = 0;
	dataMemWR = 0;
	aLUControl = 4'b0000;
	end	
endcase
endmodule



