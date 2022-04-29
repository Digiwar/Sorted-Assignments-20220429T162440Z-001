//Program Counter

module PC(in, out, clk, rst);

input clk, rst;
input [15:0] in;
output [15:0] out;
reg [15:0] out;

always @ (posedge clk)
  begin
        if (rst)
          out = 0;
        else
       	  out =	in;
  end
endmodule