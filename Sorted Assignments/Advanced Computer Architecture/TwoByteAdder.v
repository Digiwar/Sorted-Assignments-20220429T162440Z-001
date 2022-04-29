module TwoByteAdder(A, R);

// I/O ports
input [15:0] A;
output reg signed [15:0] R;


always @( * )
begin
  R = A + 2;
end

endmodule