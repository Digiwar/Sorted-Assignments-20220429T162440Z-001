//Two to One Multiplexer

module TwotoOnemux(a, b, s, out);

input [15:0] a, b;
input s;
output [15:0] out;
reg [15:0] out;
// used in procedural statement
always @ (s or a or b)
        case (s)
                2'b0: out = a;
                2'b1: out = b;
        endcase
endmodule