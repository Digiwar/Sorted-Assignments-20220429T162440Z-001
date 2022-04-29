`include "hazardUnit.v"



module hazardUnit_fixture;

reg [12:0] errBit;
wire [2:0] wrENLines;


hazardUnit testHazard(errBit,wrENLines[2],wrENLines[1],wrENLines[0]);



initial
begin
#1;$monitor("EX reg = %b",errBit[3:0],
" ID reg1 = %b",errBit[11:8],
" ID reg2 = %b",errBit[7:4],
" READ ACTIVE = %b",errBit[12],
" WR ENABLES = %b",wrENLines);
end


initial
begin
#10; errBit = 13'b0000000000000;
#10; errBit = 13'b1010011000100;
#10; errBit = 13'b1010011001100;
end



endmodule









