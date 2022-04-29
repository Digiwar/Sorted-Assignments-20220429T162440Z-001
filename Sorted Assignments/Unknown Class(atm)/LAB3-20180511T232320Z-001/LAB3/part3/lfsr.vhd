
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
 

 
entity lfsr is
 
            	port (reset, enable, clk : IN STD_LOGIC;
            	                            	Q : out STD_LOGIC_VECTOR(3 downto 0));
            	
end lfsr;
 
architecture Behavioral of lfsr is
signal W: std_logic_vector( 4 downto 1) := ( 1=>'1', others => '0' );
 
begin
 
            	process( clk, reset, enable )
            	begin
                            	if (reset = '1') then
                                            	W <= ( 1=>'1', others => '0' );
                            	elsif (enable = '1') then
                                            	if (rising_edge (clk)) then
                                                            	W <= W(3 downto 2) & ( W(1) xor W(4) ) & W(4);
                                            	end if;
                            	end if;
            	end process;
            	Q <= W;
 
 
end Behavioral;

