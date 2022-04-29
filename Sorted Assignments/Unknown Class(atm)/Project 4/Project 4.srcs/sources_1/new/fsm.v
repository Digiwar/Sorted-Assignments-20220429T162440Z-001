`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 05/11/2018 11:58:36 AM
// Design Name: 
// Module Name: fsm
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


module fsm(RESET,CLK,CLR,W,CE,SEL,S);
    input RESET,CLK;
    output CLR;
    output [2:0] W,S;
    output [1:0] SEL;
    output [3:0] CE;
    
    reg CLR;
    reg [2:0] W,S;
    reg [1:0] SEL;
    reg [3:0] CE;
    reg [3:0] cs, ns;

    parameter s_reset = 			4'b0000,
              s_1 =         4'b0001,
              s_2 =         4'b0010,
              s_3=   4'b0011,
              s_4 = 4'b0100,
              s_5 = 4'b0101,
              s_6 = 4'b0110,
              s_7 =4'b0111,
              s_8 =4'b1000,
              s_9 =4'b1001;
              //s_slot_1 =         5'b00011,
              //s_slot_2=         5'b00100,
              //s_slot_3 =         5'b00101,
              //s_select_1on4 =         5'b00110,
              //s_select_2on4 =         5'b00111,
              //s_select_3on4 =         5'b01000,
              //s_select_4on4 =         5'b01001,
              //s_display =         5'b01010,
              //s_pass_y3 =         5'b01011,
              //s_shf_p3 =         5'b01100,
              //s_shf_b3 =         5'b01101,
              //s_en_add4 =         5'b01110,
              //s_load_p4 =         5'b01111,
              //s_shf_p4 =            5'b10000;
    always@ (posedge RESET or posedge CLK)
    begin
    if(RESET) 
    cs<=s_reset;
    else cs<=ns;
    end
    
    always@ (cs)
    begin
        case(cs)
        s_reset: 
            begin
            CLR = 1;
            W = 3'b000;
            S = 3'b000;
            SEL = 2'b00;
            CE = 4'b0000;
            end
        s_1: 
                begin
                CLR = 0;
                W = 3'b000;
                S = 3'b010;
                SEL = 2'b01;
                CE = 4'b0010;
                end
        s_2: 
                begin
                CLR = 0;
                W = 3'b000;
                S = 3'b010;
                SEL = 2'b01;
                CE = 4'b1010;
                end
        s_3: 
                begin
                CLR = 0;
                W = 3'b010;
                S = 3'b010;
                SEL = 2'b00;
                CE = 4'b0010;
                end
        s_4:
            begin
            W = 3'b000;
            S = 3'b010;
            SEL = 2'b00;
            CE = 4'b0001;
            end
        s_5:
            begin
            W = 3'b000;
            S = 3'b001;
            SEL = 2'b01;
            CE = 4'b0000;
            end
        s_6:
            begin
            W = 3'b100;
            S = 3'b001;
            SEL = 2'b01;
            CE = 4'b1000;
            end
        s_7:
            begin
            W = 3'b100;
            S = 3'b001;
            SEL = 2'b01;
            CE = 4'b0100;
            end
        s_8:
            begin
            W = 3'b000;
            S = 3'b001;
            SEL = 2'b01;
            CE = 4'b0000;
            end
      
        endcase
    end
    always @ (cs)
    begin
        case(cs)
            s_reset   :            ns <= s_1;        
            s_1   :            ns <= s_2;
            s_2  :            ns <= s_3;
            s_3   :            ns <= s_4;
            s_4  :            ns <= s_5;
            s_5   :            ns <= s_6;
            s_6  :            ns <= s_7;
            s_7   :            ns <= s_8;
            //s_reset   :            ns = s_store_A;
         
            default :            ns = s_reset;
        endcase
    end
endmodule
