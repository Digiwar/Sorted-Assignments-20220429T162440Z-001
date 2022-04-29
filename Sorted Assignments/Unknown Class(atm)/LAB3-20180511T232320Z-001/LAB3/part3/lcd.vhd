
library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
use ieee.std_logic_arith.all;
entity lcd is
            	port (
            	
            	f: in std_logic_vector(3 downto 0);
            	lcd_e, lcd_rs, lcd_rw, lcd_7, lcd_6, lcd_5, lcd_4, sf_ce0 : out std_logic;
            	clk : in std_logic);
end lcd;
 
 
architecture beh of lcd is
signal lcd_code_temp : std_logic_vector (7 downto 0);
signal lcd_code_temp2 : std_logic_vector (7 downto 0);
signal lcd_code_temp3 : std_logic_vector (7 downto 0);
signal lcd_code : std_logic_vector (5 downto 0);
signal lcd_stuff : std_logic_vector (6 downto 0);
signal lcd_stb, lcd_busy, lcd_rw_temp: std_logic;
signal count : std_logic_vector (25 downto 0);
type state_type is (s1_idle,s2_display);
signal state: state_type ;
begin
            	process(clk)
            	begin
                            	if(rising_edge(clk)) then
                                            	count <= count + 1;
                                            	lcd_busy <= '1';
                                            	sf_ce0 <= '1';
                                            	case count(25 downto 20) is
                                                            	when "000000" => lcd_code <= "000011";
                                                            	when "000001" => lcd_code <= "000011";
                                                            	when "000010" => lcd_code <= "000011";
                                                            	when "000011" => lcd_code <= "000010";
                                                            	when "000100" => lcd_code <= "000010";
                                                            	when "000101" => lcd_code <= "001000";
                                                            	when "000110" => lcd_code <= "000000";
                                                            	when "000111" => lcd_code <= "000110";
                                                            	when "001000" => lcd_code <= "000000";
                                                            	when "001001" => lcd_code <= "001100";
                                                            	when "001010" => lcd_code <= "000000";
                                                            	when "001011" => lcd_code <= "000001";
                                                            	-- Characters
                                                            	-- Upper bit
                                                            	-- Lower bit
                                                            	when "001100" => lcd_code <= "10" & "0011";
                                                            	when "001101" => lcd_code <= "10000" & f(3);
                                            	            	
                                                            	
                                                            	when "001110" => lcd_code <= "10" & "0011";
                                                            	when "001111" => lcd_code <= "100" & f(2 downto 0);
                                                            	
                                                            	--when "010000" => lcd_code <= "10" & "0011";
                                                            	--when "010001" => lcd_code <= "10" & "0011";
                                                            	when others => lcd_code <= "010000";
                                            	end case;
 
 
                                            	lcd_stb <= (count(19) xor count(18)) and (not(lcd_rw_temp)) and lcd_busy;
                                            	lcd_e <= lcd_stb;
                                            	lcd_rs <= lcd_code(5);
                                            	lcd_rw <= lcd_code(4);
                                            	lcd_7 <= lcd_code(3);
                                            	lcd_6 <= lcd_code(2);
                                            	lcd_5 <= lcd_code(1);
                                            	lcd_4 <= lcd_code(0);
                            	end if;
            	end process;
end beh;
