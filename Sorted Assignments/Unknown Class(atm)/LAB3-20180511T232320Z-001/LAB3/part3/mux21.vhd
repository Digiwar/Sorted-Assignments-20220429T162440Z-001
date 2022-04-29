
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
 
 
entity mux2to1 is
 
            	port (sel : in std_logic;
                                            	LFSR, CALC: in std_logic_vector(3 downto 0);
                                            	Y : out std_logic_vector(3 downto 0));
 
end mux2to1;
 
architecture Behavioral of mux2to1 is
signal W : std_logic_vector(3 downto 0 );
begin
            	process(sel, LFSR, CALC)
            	begin
            	
                            	if (sel = '0') then
                                            	W <= CALC;
                            	else
                                            	W <= LFSR;
                            	end if;
            	
            	end process;
            	
            	Y <= W;
 
end Behavioral;
