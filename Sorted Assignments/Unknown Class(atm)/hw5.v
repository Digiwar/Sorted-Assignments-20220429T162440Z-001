module hw5 (wire [31:0] DAT_IN,
		 input CLOK,RSET,W_ABLE,
		 output reg [31:0]  DAT_OUT);
//input reg [31:0] DAT_IN;
//reg CLOK,RSET,W_ABLE;
//output wire [31:0] DAT_OUT;


always@(posedge CLOK)
begin
	if(!RSET)
		DAT_IN = 32'b0;
	else
		if(W_ABLE)
		DAT_OUT=DAT_IN;
end

endmodule


