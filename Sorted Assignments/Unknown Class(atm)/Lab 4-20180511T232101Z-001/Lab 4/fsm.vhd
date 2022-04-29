library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity fsm is

	port(
	
		reset:   in std_logic;
		clk:     in std_logic;
		clr:     out std_logic;
		w:       out std_logic_vector(2 downto 0);
		ce:      out std_logic_vector(3 downto 0);
		sel:     out std_logic_vector(1 downto 0);
		s:       out std_logic_vector(2 downto 0)
		
	);
	
end fsm;

architecture bhv of fsm is

type state is(s_reset, s0, s1, s2, s3, s4, s5, s6, s7, s8, s9);
signal cs: state;

begin

	process(clk, reset)
	begin
	
		if(reset = '1') then 
		
			cs <= s_reset;
			
		elsif( rising_edge(clk)) then 
		
			case(cs) is
			
				when s_reset =>
				
					cs <= s0;
				
				when s0 =>
				
					cs <= s1;
				
				when s1 =>
				
					cs <= s2;
				
				when s2 =>
				
					cs <= s3;
				
				when s3 =>
				
					cs <= s4;
				
				when s4 =>
				
					cs <= s5;
				
				when s5 =>
				
					cs <= s6;
				
				when s6 =>
				
					cs <= s7;
				
				when s7 =>
				
					cs <= s8;
				
				when s8 =>
				
					cs <= s9;
				
				when s9 =>
				
					cs <= s9;
					
				when others =>
				
					cs <= s_reset;
				
			end case;
			
		end if;
		
	end process;
	
	
	
	process(cs)
	begin
	
		case(cs) is
		
			when s_reset =>
				
				clr <= '1';
				w <= "000";
				ce <= "0000";
				sel <= "00";
				s <= "000";
				
			when s0 =>
				
				clr <= '0';
				w <= "000";
				ce <= "0010";
				sel <= "01";
				s <= "010";
				
			when s1 =>
				
				w <= "000";
				ce <= "1010";
				sel <= "01";
				s <= "010";
				
			when s2 =>
				
				w <= "010";
				ce <= "0010";
				sel <= "00";
				s <= "010";
				
			when s3 =>
				
				w <= "000";
				ce <= "0001";
				sel <= "00";
				s <= "010";
				
			when s4 =>
				
				w <= "000";
				ce <= "1001";
				sel <= "00";
				s <= "010";
				
			when s5 =>
				
				w <= "001";
				ce <= "0001";
				sel <= "00";
				s <= "010";
				
			when s6 =>
				
				w <= "000";
				ce <= "0000";
				sel <= "01";
				s <= "001";
				
			when s7 =>
				
				w <= "100";
				ce <= "1000";
				sel <= "01";
				s <= "001";
				
			when s8 =>
				
				w <= "100";
				ce <= "0100";
				sel <= "01";
				s <= "001";
				
			when s9 =>
				
				w <= "000";
				ce <= "0000";
				sel <= "01";
				s <= "001";
				
			when others =>
				
				clr <= '1';
				w <= "000";
				ce <= "0000";
				sel <= "00";
				s <= "000";
				
		end case;
			
	end process;
	
end bhv;