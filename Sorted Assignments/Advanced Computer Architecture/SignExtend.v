//Sign Extend Unit

module SignExtend(in, extended_out);

input [3:0] in;
output reg [15:0] extended_out;


always @ (*)
begin
   extended_out = { {8{in[3]}}, in};
end
endmodule

