
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
 

entity compartor is
 
            	port (d1, d2 : in std_logic_vector(2 downto 1);
                                            	y : out std_logic);
 
end compartor;
 
architecture Behavioral of compartor is
 
begin
 
            	process ( d1, d2)
            	begin
 
                            	if (d1 > d2) then
                                            	y <= '1';
                            	else
                                            	y <= '0';
                            	end if;
 
            	end process;
 
 
end Behavioral;

