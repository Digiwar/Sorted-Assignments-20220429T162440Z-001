-------------------------------------------------------------------------------------------------------------------------------


library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
use ieee.std_logic_arith.all;
entity clkdiv is
    port ( osc:  in std_logic;	
           clk: out std_logic );
end clkdiv;
architecture beh1 of clkdiv is 
signal  cnt:  std_logic_vector( 25 downto 0);-- 
begin
     process(osc)
     begin
        if (rising_edge(osc)) then
          if (cnt = 75000000) then          
             cnt <= (others=>'0');
             clk <= '1';
          elsif( cnt < 35000000 ) then    
             cnt <= cnt + 1;
             clk <= '1';
          else
             cnt <= cnt + 1;
             clk <= '0';
          end if;
        end if;
     end process;
end beh1;

--------------------------------------------------------------------------------------------------------------------------------


LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
ENTITY lfsr IS
	PORT(clr, clk: IN STD_LOGIC;
		q : OUT STD_LOGIC_VECTOR(4 downto 1));
END lfsr;
ARCHITECTURE beh2 OF lfsr IS
signal w: std_logic_vector(4 downto 1);
BEGIN
	process(clr,clk)
	begin
		if (clr='1') then
			w <= ( 1=>'1', others => '0' );
		elsif (falling_edge(clk)) then
			w <= w(3 downto 2) & ( w(1) xor w(4) ) & w(4);
		end if;
	end process;
	q <= w;
END beh2;   
--------------------------------------------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
entity dffs is
	port(load,clr: in std_logic;
			data_lfsr : in std_logic_vector(3 downto 0);
			out_lfsr : out std_logic_vector(3 downto 0));
end dffs;
architecture beh3 of dffs is
begin
	process(clr,load)
	begin
		if(clr = '1') then
			out_lfsr <= "0000";
		elsif(load ='1') then
			out_lfsr <= data_lfsr;
		end if;
	end process;
end beh3;
--------------------------------------------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
entity ham is
	port(d: in std_logic_vector(4 downto 1);
			o : out std_logic_vector(6 downto 0));
end ham;
architecture beh4 of ham is
signal p1,p2,p3: std_logic;
begin 
	p1<= d(4) xor d(2) xor d(1);
	p2<= d(4) xor d(3) xor d(1);
	p3<= d(4) xor d(3) xor d(2);
	o <= d(4) & d(3) & d(2) & p3 & d(1) & p2 & p1;
end beh4;
--------------------------------------------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
entity mux is
	port(a,b,c,d : in std_logic_vector(6 downto 0);
			sel : in  std_logic_vector(1 downto 0);
			led : out std_logic_vector(6 downto 0));
end mux;
architecture beh6 of mux is
begin
	process (a, b, c, d, sel)
	begin
		case sel is
			when "00" => led <= a;
			when "01" => led <= b;
			when "10" => led <= c;
			when "11" => led <= d;
			when others => led <= a;
		end case;
	end process;
end beh6;
--------------------------------------------------------------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
entity fsm is
port(sw1,sw2,sw3,clk : IN std_logic;
		load,clr : OUT std_logic;
		sel : out std_logic_vector(1 downto 0));
end entity;

architecture beh5 of fsm is
type state_type is (idle,display_lfsr,hold_lfsr,display_ham);
signal cs, ns: state_type;
begin
	reset: process(clk) begin
		if(rising_edge(clk)) then
			if(sw1='1') then 
				cs<=idle;
			else
				cs<=ns;
			end if;
		end if;
	end process reset;
	switch: process(cs,sw1,sw2,sw3)begin
		case cs is 
		when idle =>
			if sw2='1' then
				ns <= display_lfsr;
			elsif sw3='1' then
				ns <= display_ham;
			else
				ns <= idle;
			end if;
		when display_lfsr =>
			if sw2='1' then
				ns <= display_lfsr;
			elsif sw3='1' then
				ns <= display_ham;
			elsif sw2='0' then
				ns <= hold_lfsr;
			else
				ns <= idle;
			end if;
		when hold_lfsr =>
			if sw2='1' then
				ns <= display_lfsr;
			elsif sw3='1' then
				ns <= display_ham;
			elsif sw2='0' then
				ns <= hold_lfsr;
			else
				ns <= idle;
			end if;
		when display_ham =>
			if sw3='1' then
				ns <= display_ham;
			elsif sw2='1' then
				ns <= display_lfsr;
			else
				ns <= idle;
			end if;
		end case;
	end process switch;
	outp: process(cs) begin
		case cs is
		when idle => 
			load<= '0';
			clr <= '1';
			if(sw1='1') then
				sel <= "01";
			else 
				sel<= "00";
			end if;
		when display_lfsr =>
			load<= '1';
			clr <= '0'; 
			if(sw2='1') then
				sel<="10";
			else 
				sel<= "00";
			end if;
		when hold_lfsr =>
			load<= '0';
			clr <= '0'; 
			if(sw2='0') then
				sel<="10";
			else 
				sel<= "00";
			end if;
		when display_ham => 
			load<= '0';
			clr <= '0';
			if(sw3='1') then
				sel<="11";
			else 
				sel<= "00";
			end if;
		when others => 
			sel <= "00";
			load<= '0';
			clr <= '0';
		end case;
	end process outp;
end beh5;
--------------------------------------------------------------------------------------------------------------------------------

-- top 
library ieee;
use ieee.std_logic_1164.all;
entity top is
port(osc, sw1, sw2, sw3: in std_logic;
--port(clk, sw1, sw2, sw3: in std_logic;
		led: out std_logic_vector(6 downto 0));
end entity;
architecture beh7 of top is
-- internal wire names for mapping
signal clk,clr,load:	std_logic;
signal sel:	std_logic_vector(1 downto 0);
signal data_lfsr,out_lfsr: std_logic_vector(3 downto 0);
signal led_ham,led_lfsr: std_logic_vector(6 downto 0);

component clkdiv
port(osc: in std_logic;
		clk: out std_logic);
end component;

component lfsr
port(clr, clk: in std_logic;
			q : out std_logic_vector(4 downto 1));
end component;

component dffs
port(load,clr: in std_logic;
		data_lfsr: in std_logic_vector(3 downto 0);
		out_lfsr: out std_logic_vector(3 downto 0) );
end component;

component ham
port(d : in std_logic_vector(3 downto 0);
		o : out std_logic_vector(6 downto 0));
end component;

component fsm
port(sw1,sw2,sw3,clk : IN std_logic;
		load,clr : OUT std_logic;
		sel : out std_logic_vector(1 downto 0));
end component;

component mux
port(a,b,c,d: in std_logic_vector(6 downto 0);
		sel: in std_logic_vector(1 downto 0);
	   led: out std_logic_vector(6 downto 0));
end component;
-- begin making connections
begin
 g1: clkdiv port map(osc,clk);
 g2: fsm port map(sw1,sw2,sw3,clk,load,clr,sel);
 g3: lfsr port map(clr,clk,data_lfsr);
 g4: dffs port map(load,clr,data_lfsr,out_lfsr);
 g5: ham port map(out_lfsr,led_ham);
 led_lfsr <= "000" & out_lfsr;
 g6: mux port map("0000000", "0000000", led_lfsr, led_ham, sel, led);
end beh7;




