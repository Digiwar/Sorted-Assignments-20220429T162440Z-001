module adder( a, b, cout, s, STRT);
input [3:0]  a, b;
input STRT;
output cout;
output [3:0] s;


assign  { cout, s } = a+b;

endmodule


