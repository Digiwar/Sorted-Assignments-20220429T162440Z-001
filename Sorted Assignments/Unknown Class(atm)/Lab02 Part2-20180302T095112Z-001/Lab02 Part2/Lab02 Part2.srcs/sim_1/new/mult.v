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


module mult(display,multicand,multiplier,RST,STRT,P, CLK,shft_con,ld1,ld2,loadP);
    input [3:0] multicand, multiplier;
    input RST, STRT, CLK, shft_con, ld1,ld2,loadP;
    wire [3:0] j,k,l,m,n;
    wire s;
    output [7:0]display;
    
    //assign l = 4'b0111;
    input [7:0] P;
    
    

            dffa load1(.STRT(STRT),.clk(CLK),.clr(RST),.da(multicand),.load(ld1),.qa(j));
            dffb load2(.STRT(STRT),.clk(CLK),.clr(RST),.db(multiplier),.qb(k),.sft(shft_con),.load(ld2));
            mux choose1(.STRT(STRT),.s(k[0]),.d1(4'b0000),.d0(multicand),.y(n));
            adder add1(.a(P[7:4]),.b(n),.cout(P[7]),.s(m),.STRT(STRT));
            shiftp shift1(.loadP(loadP),.P(P),.carry(P[7]),.sum(m),.shift(shft_con),.clk(CLK),.rst(RST));
    
    assign display = P;
endmodule
