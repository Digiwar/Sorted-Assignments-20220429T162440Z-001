module mux ( s, d1, d0, y );
input  d1,d0;
input   s;  
output  y;


reg   [3:0] y;

always@(s or d1 or d0)
case (s)
    0: y =d0;
    1: y=d1;
endcase

endmodule



