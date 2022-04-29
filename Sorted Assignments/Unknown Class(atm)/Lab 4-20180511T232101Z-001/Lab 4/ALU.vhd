library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity ALU is

	port(
	
		a:     in std_logic;
		b:     in std_logic;
		cin:   in std_logic;
		s:     in std_logic_vector(2 downto 0);
		y:     out std_logic
		
	);
	
end ALU;

architecture bh of ALU is 
begin

	process(a, b, cin, s)
	begin
	
		case(s) is
		
			when "000" =>
			
				y <= (a xor b xor cin);
			
			when "001" =>
			
				y <= (a xor (not b) xor cin);
			
			when "010" =>
			
				y <= b;
			
			when "011" =>
			
				y <= (a nand b);
			
			when "100" =>
			
				y <= (a and b);
			
			when "101" =>
			
				y <= (a or b);
			
			when "110" =>
			
				y <= (not a);
			
			when others =>
			
				y <= (a xor b);
			
		end case;
		
	end process;
	
end bh;