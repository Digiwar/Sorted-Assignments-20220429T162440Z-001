module adder( a, b, cout, s);
input [3:0]  a, b;
output cout;
output [3:0] s;

assign  {cout, s} = a+b;

endmodule


