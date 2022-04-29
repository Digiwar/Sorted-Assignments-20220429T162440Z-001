//ALU Control Unit


module ALUControl(instruc,control);

input [3:0] instruc;
//leading bit used for mux source in mem stage
output reg [4:0] control;

always @ (*)begin

case(instruc)
//add control signal
4'b0000: control = 5'b00000;
//subtract control signal
4'b0001: control = 5'b00001;
//multiplication control signal
4'b0010: control = 5'b00010;
//division control signal
4'b0011: control = 5'b00011;
//move control signal
4'b0100: control = 5'b00100;
//swap control signal
4'b0101: control = 5'b10101;
//AND control signal
4'b0110: control = 5'b00110;
//OR control signal
4'b0111: control = 5'b00111;
//LOAD/STORE control signal
4'b1000: control = 5'b01000;
//Branch control signal
4'b1001: control = 5'b01001;

4'b1010:;

4'b1011:;

4'b1100:;

4'b1101:;

1110:;

1111:;
default: control = 0;
endcase
end
endmodule
