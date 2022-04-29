module test(a,b, l1,l2,l3,l4);
input a,b;
output l1,l2,l3,l4;


assign l1 =(~a)& (~b);
assign l2 = (~a) & b;
assign l3 = a & (~b);
assign l4 = a & b;

endmodule 