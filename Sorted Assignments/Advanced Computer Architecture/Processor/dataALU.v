//Arithmetic Unit

module dataALU(Op1,Op2,Control,data_out,signFlag);



//I/O

input signed [16:0]Op1,Op2;
input [3:0]Control;
output reg [32:0]  data_out;
output reg signFlag;


always @ (*)
begin
case(Control)
4'b0000:begin 
	data_out = Op1 + Op2;
	signFlag = data_out[32];
end

4'b0001:begin
	data_out = Op1 - Op2;
	signFlag = data_out[32];
end
4'b0010:data_out = Op1 / Op2;
4'b0011:data_out = Op1 * Op2;
4'b0100:data_out = Op2 + 32'b0;
4'b0101:data_out = Op1;
4'b0110:data_out = Op1 & 8'b0; 
4'b0111:data_out = Op1 | 8'b0;
4'b1000:data_out = (31'b0 | (Op1 + Op2));
4'b1001:;

default: data_out = 0;

endcase
end
endmodule
