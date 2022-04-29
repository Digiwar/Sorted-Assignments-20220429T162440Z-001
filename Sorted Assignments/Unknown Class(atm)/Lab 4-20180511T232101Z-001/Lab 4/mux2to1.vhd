library ieee;
use ieee.std_logic_1164.all;

entity mux2to1 is

	port(
	
		d0:   in std_logic;
		d1:   in std_logic;
		s:    in std_logic;
		y:    out std_logic
		
	);
	
end mux2to1;

architecture behavior of mux2to1 is
begin

	process(d0, d1, s)
	begin
	
		if( s = '1' ) then
		
			y <= d1;
			
		else
		
			y <= d0;
			
		end if;
	
	end process;

end behavior;