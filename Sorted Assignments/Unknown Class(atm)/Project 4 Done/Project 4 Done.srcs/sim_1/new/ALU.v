`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 05/11/2018 12:18:52 PM
// Design Name: 
// Module Name: ALU
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


module ALU(B,A, S,Cin,Y );
    input  B,A;
    input [2:0] S;
    input Cin;
    output  Y;
    
    reg  Y;
    
    always@(S or B or A)
    case(S)
        0:assign Y = A^B^Cin;
        1:assign Y = A^(~B)^Cin;
        2:assign Y = B;
        3:assign Y = ~(A&B);
        4:assign Y = A&B;
        5:assign Y = A|B;
        6:assign Y = ~A;
        7:assign Y = (A^B);
    endcase
endmodule
