module mux ( s, d1, d0, y);
input [3:0]  d1, d0;
input        s;
output [3:0] y;


reg   [3:0] y;

always@(s or d1 or d0 )
begin

   if (~s)
          y <= d1;
   else
          y <= d0;

end

endmodule



