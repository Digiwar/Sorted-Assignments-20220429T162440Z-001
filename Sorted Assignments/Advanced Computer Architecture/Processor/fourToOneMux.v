//4-1 Mux

module fourToOneMux(a,b,c,d,s,out);

//I/O declartions
input [15:0] a,b,c,d;
input [2:0] s;
output reg [15:0] out;


always @ (*)
begin
	case(s)
	4'b0001: out = a;
	4'b0010: out = b;
	4'b0100: out = c;
	4'b1000: out = d;
	endcase
end
endmodule
