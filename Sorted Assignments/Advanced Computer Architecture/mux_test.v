`include "TwotoOnemux.v"

module mux_tb;

reg [15:0] a;
reg [15:0] b;
reg [15:0] s;
wire [15:0] out;

TwotoOnemux my_mux( a, b, s, out );

initial
begin
   #1 $monitor("a = %b ", a, "b = %b", b, "  |  s = ", s, "  |  out = ", out );

      a = 10; b = 0; s = 1'b0;
      #1;
      a = 0; b = 1; s = 1'b0;
      #1;
      a = 13; b = 0; s = 1'b0;
      #1;
      a = 12; b = 1; s = 1'b0;
      #1;
      a = 10; b = 0; s = 1'b1;
      #1;
      a = 13; b = 1; s = 1'b1;
      #1;
      a = 12; b = 0; s = 1'b1;
      #1;
      a = 10; b = 1; s = 1'b1;
      #1;

    $finish;
end
endmodule