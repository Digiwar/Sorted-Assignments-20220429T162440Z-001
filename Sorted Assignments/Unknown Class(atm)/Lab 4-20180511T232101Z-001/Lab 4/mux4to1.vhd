library ieee;
use ieee.std_logic_1164.all;

entity mux4to1 is

	port(
	
		d0:   in std_logic;
		d1:   in std_logic;
		d2:   in std_logic;
		d3:   in std_logic;
		s:    in std_logic_vector(1 downto 0);
		y:    out std_logic
		
	);
	
end mux4to1;

architecture beh of mux4to1 is
begin

	process(d0, d1, d2, d3, s)
	begin
	
		case(s) is 
	
			when "00" =>
		
				y <= d0;
			
			when "01" =>
		
				y <= d1;
			
			when "10" =>
		
				y <= d2;
			
			when others =>
		
				y <= d3;
			
		end case;
	
	end process;

end beh;