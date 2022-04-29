`include "hw5.v"

module hw5_fixture;

reg clk,rst,w_enable;
wire [31:0] data_in,data_out;



hw5 inst1 (data_in,clk,rst,w_enable,data_out);
initial
begin
	clk = 1'b0;
	forever #10 clk = ~clk;
end

initial
begin
	rst = 1'b1;
	data_in =  32'd40;
	#30 rst = 1'b0;
	#60 rst = 1'b1;w_enable = 1'b1;data_in=32'd100;
end
endmodule

