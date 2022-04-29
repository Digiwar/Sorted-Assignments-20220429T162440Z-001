module preg(clk, reset, load, shift, cin, din_p, p, ph);
input clk, reset, load, shift, cin;
input[3:0]din_p;
output[7:0]p;
output[3:0]ph;
reg[3:0]ph, pl;
always@(posedge clk or posedge reset)
begin
	if (reset)
		ph <= 4'h0;
	else if (load)
		ph <= din_p;
	else if (shift)
		ph <= { cin , ph[3:1] };
end
always@(posedge clk or posedge reset)
begin
	if (reset)
		pl <= 4'h0;
	else if (shift)
		pl <= { ph[0] , pl[3:1] };
end
assign p = {ph, pl};
endmodule 
