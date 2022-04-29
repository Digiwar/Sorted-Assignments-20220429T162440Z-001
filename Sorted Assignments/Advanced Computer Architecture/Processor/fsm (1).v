module fsm(reset, clk, clr, load_ab, load_p, shf_b, shf_p, en_add);

input	reset, clk;
output	clr, load_ab, load_p, shf_b, shf_p, en_add;
reg		clr, load_ab, load_p, shf_b, shf_p, en_add;
reg[4:0]	cs, nexts;

parameter s_clr = 			5'b00000,
          s_load_ab = 		5'b00001,
			 s_en_add1 = 		5'b00010,
			 s_load_p1 = 		5'b00011,
			 s_shf_p1 = 		5'b00100,
			 s_shf_b1 = 		5'b00101,
			 s_en_add2 = 		5'b00110,
			 s_load_p2 = 		5'b00111,
			 s_shf_p2 = 		5'b01000,
			 s_shf_b2 = 		5'b01001,
			 s_en_add3 = 		5'b01010,
			 s_load_p3 = 		5'b01011,
			 s_shf_p3 = 		5'b01100,
			 s_shf_b3 = 		5'b01101,
			 s_en_add4 = 		5'b01110,
			 s_load_p4 = 		5'b01111,
			 s_shf_p4 =			5'b10000,
			 s_wait =			5'b10001;

always @ (posedge clk or posedge reset)
begin
	if(reset) cs <= s_clr;
	else cs <= nexts;
end

always @ (cs)
begin
	case(cs)
		s_clr	:			nexts = s_load_ab;		
		s_load_ab :			nexts = s_en_add1;		
		s_en_add1 :			nexts = s_load_p1;
		s_load_p1 :			nexts = s_shf_p1;
		s_shf_p1 :			nexts = s_shf_b1;
		s_shf_b1 :			nexts = s_en_add2;
		s_en_add2 :			nexts = s_load_p2;
		s_load_p2 :			nexts = s_shf_p2;
		s_shf_p2 :			nexts = s_shf_b2;
		s_shf_b2 :			nexts = s_en_add3;
		s_en_add3 :			nexts = s_load_p3;
		s_load_p3 :			nexts = s_shf_p3;
		s_shf_p3 :			nexts = s_shf_b3;
		s_shf_b3 :			nexts = s_en_add4;
		s_en_add4 :			nexts = s_load_p4;
		s_load_p4 :			nexts = s_shf_p4;
		s_shf_p4:			nexts = s_wait;
		s_wait:				nexts = s_wait;
		
		default :			nexts = s_clr;
	endcase
end

always @ (cs)
begin
	case(cs)
		s_clr:
		begin
			clr = 1;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 0;
			en_add = 0;
		end
		s_load_ab:
		begin
			clr = 0;
			load_ab = 1;
			load_p = 0;
			shf_b = 0;
			shf_p = 0;
			en_add = 0;
		end
		s_en_add1:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 0;
			en_add = 1;
		end
		s_load_p1:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 1;
			shf_b = 0;
			shf_p = 0;
			en_add = 0;
		end
		s_shf_p1:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 1;
			en_add = 0;
		end
		s_shf_b1:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 1;
			shf_p = 0;
			en_add = 0;
		end
		s_en_add2:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 0;
			en_add = 1;
		end
		s_load_p2:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 1;
			shf_b = 0;
			shf_p = 0;
			en_add = 0;
		end
		s_shf_p2:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 1;
			en_add = 0;
		end
		s_shf_b2:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 1;
			shf_p = 0;
			en_add = 0;
		end
		s_en_add3:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 0;
			en_add = 1;
		end
		s_load_p3:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 1;
			shf_b = 0;
			shf_p = 0;
			en_add = 0;
		end
		s_shf_p3:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 1;
			en_add = 0;
		end
		s_shf_b3:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 1;
			shf_p = 0;
			en_add = 0;
		end
		s_en_add4:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 0;
			en_add = 1;
		end
		s_load_p4:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 1;
			shf_b = 0;
			shf_p = 0;
			en_add = 0;
		end
		s_shf_p4:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 1;
			en_add = 0;
		end
		s_wait:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 0;
			en_add = 0;
		end
		default:
		begin
			clr = 0;
			load_ab = 0;
			load_p = 0;
			shf_b = 0;
			shf_p = 0;
			en_add = 0;
		end
	endcase
end

endmodule 