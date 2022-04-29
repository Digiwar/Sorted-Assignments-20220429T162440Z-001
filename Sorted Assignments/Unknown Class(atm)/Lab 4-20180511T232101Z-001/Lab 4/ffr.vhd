library ieee;
use ieee.std_logic_1164.all;

entity ffr is

	port(
	
		clk:     in std_logic;
		ce:      in std_logic;
		reset:   in std_logic;
		d:       in std_logic;
		q:       out std_logic
		
	);
	
end ffr;

architecture behave of ffr is
begin 

	process(clk, ce, reset, d)
	begin
	
		if(reset = '1') then 
		
			q <= '0';
			
		elsif( rising_edge(clk) ) then 
		
			if( ce = '1' ) then 
			
				q <= d;
				
			end if;
			
		end if;
		
	end process;
	
end behave;