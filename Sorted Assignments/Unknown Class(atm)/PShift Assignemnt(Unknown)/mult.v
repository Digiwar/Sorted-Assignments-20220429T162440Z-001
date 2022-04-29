`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/14/2018 02:32:01 PM
// Design Name: 
// Module Name: mult
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module mult(multicand,multiplier,RST,STRT,P, CLK,shft_con,ld1,ld2);
    input [3:0] multicand, multiplier;
    input RST, STRT, CLK, shft_con, ld1,ld2;
    wire [3:0] j,k,l,m,n;
    wire s;
    assign l = 4'b0111;
    output [7:0] P;
    

            dffa load1(.STRT(STRT),.clk(CLK),.clr(RST),.da(multicand),.load(ld1),.qa(j));
            dffb load2(.STRT(STRT),.clk(CLK),.clr(RST),.db(multiplier),.qb(k),.sft(shft_con),.load(ld2));
            mux choose1(.STRT(STRT),.s(k[0]),.dl(l),.d0(multicand),.y(n));
            adder add1(.STRT(STRT),.a(j),.b(P[7:3]),.cout(P[7]),.s(m));
            assign P = P[6:0] >> 1;

    
endmodule
