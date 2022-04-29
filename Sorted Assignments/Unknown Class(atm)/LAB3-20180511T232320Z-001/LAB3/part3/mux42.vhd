
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
 
 
entity demux4to2 is
 
            	port (Q : in std_logic_vector(4 downto 1);
                                            	D3, D4 : out std_logic_vector(2 downto 1)
            	);
            	
end demux4to2;
 
architecture Behavioral of demux4to2 is
 
 
 
begin
 
            	D3 <=  (Q(2) & Q(1));
            	
            	D4 <= (Q(4) & Q(3));
            	
 
end Behavioral;

