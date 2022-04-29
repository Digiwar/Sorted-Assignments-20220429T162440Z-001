
library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity fsm is
	port(
		clk1, reset, sw1, sw2, sw3: in std_logic;
		m: out std_logic_vector(7 downto 1));
end fsm;

architecture beh of fsm is
	--parameters of states
	type state_type is (s0,s1,s2,s3);
	--current state, next state
	signal cs, ns: state_type;
	signal load, clk2: std_logic;
	signal sel: std_logic_vector(1 downto 0);
	signal lfsr_a: std_logic_vector(4 downto 1);
	signal dff_a: std_logic_vector(4 downto 1);
	signal ham: std_logic_vector(7 downto 1);
	signal output: std_logic_vector(7 downto 1);
	begin
	
	m1: entity work.lfsr port map(clk2, lfsr_a);
	m2: entity work.clkdiv port map(clk1, clk2);
	m3: entity work.hc port map(dff_a, ham);
	m4: entity work.dff port map(lfsr_a, load, dff_a);
	m5: entity work.mux port map(dff_a, ham, sel, output);
	
	process(clk2, reset)
		begin
			if(reset='1') then
				cs<=s0;
			elsif (rising_edge(clk2)) then
				cs<=ns;
			end if;
	end process;
	
	process(cs, sw1, sw2, sw3)
		begin
			case (cs) is
				when s0=>					sel<="00";
					if (sw1='1') then
	ns<=s0;				load<='1';					else
						ns<=s3; --idle state
						load<='0'; 
					end if;
				when s1=>
					sel<="01";
					if (sw2='1') then
						ns<=s1; 
						load<='1';	
					else
						ns<=s3;
						load<='0';	
					end if;
				when s2=>
					sel<="10";
					if (sw3='1') then
						ns<=s2; 
						load<='0';		
					else
						ns<=s3;
						load<='0';	
					end if;
				when s3 => 
					sel <= "11";
					if(sw1='1') then
						ns <= s0;
						load <= '1';
					elsif(sw2='1') then
						ns <=s1;
						load <= '1';
					elsif(sw3='1') then
						ns <=s2;
						load <= '1';
					else
						ns <= s3;
						load <= '0'; 
					end if;
				when others => sel <= "00";
					ns<=s3; 
					load<='0'; 
			end case;
	end process;
	
	m<=output;
end beh;
