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
          if (cnt = 80000000) then          
             cnt <= (others=>'0');
             clk <= '1';
          elsif( cnt < 40000000 ) then    
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
		elsif (rising_edge(clk)) then
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
entity encoder is
	port(q: in std_logic_vector(4 downto 1);
		d3,d4: out std_logic_vector(2 downto 0));
end encoder;
architecture beh9 of encoder is
begin 
	d3<= "0"& q(2) & q(1);
	d4<= "0"& q(4) & q(3);
end beh9;

------------------------------------------------------------------------------------------------------------------------------------------------------
library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
use ieee.std_logic_arith.all;
entity decoder is
	port(sw3,sw4: in std_logic;
		d3,d4: in std_logic_vector(2 downto 0);
		din: out std_logic_vector(2 downto 0)
		);
end decoder;

architecture beh92 of decoder is
signal t1: std_logic_vector(1 downto 0);

begin 

    t1<=sw3&sw4;
    process (sw3,sw4)
        begin
            case (t1) is
                when "00" => 
                    if(d3 < d4) then 
                        din <= "001";
                    else
                        din <= "000";
                    end if;
                when "01" => din <= (d4 + d3);
                when "10" => din <= (d4 or d3);
                when "11" => din <= (d4 and d3);
                when others => din <= "000";
            end case;
        end process;
end beh92;


--------------------------------------------------------------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
entity fsm is
port(sw1,sw2,clk : IN std_logic;
		load,clr : OUT std_logic
		);
		
end entity;


architecture beh5 of fsm is
type state_type is (idle,display_lfsr,hold_lfsr);
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
	switch: process(cs,sw1,sw2)begin
		case cs is 
		when idle =>
			if sw2='1' then
				ns <= display_lfsr;
			else
				ns <= idle;
			end if;
		when display_lfsr =>
			if sw2='1' then
				ns <= display_lfsr;
			elsif sw2='0' then
				ns <= hold_lfsr;
			else
				ns <= idle;
			end if;
		when hold_lfsr =>
			if sw2='1' then
				ns <= display_lfsr;
			elsif sw2='0' then
				ns <= hold_lfsr;
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

		when display_lfsr =>
			load<= '1';
			clr <= '0'; 
		when hold_lfsr =>
			load<= '0';
			clr <= '0'; 
		when others => 
			load<= '0';
			clr <= '0';
		end case;
	end process outp;
end beh5;
--------------------------------------------------------------------------------------------------------------------------------
library ieee;
use ieee.std_logic_1164.all;
entity lcd is
port(clk,clr, load : in std_logic;
	din: in std_logic_vector(2 downto 0);
	seg: out std_logic_vector(7 downto 0)
	);
end lcd;
ARCHITECTURE arch OF lcd IS
BEGIN

process (clk)
  begin
	seg(7) <= '1'; 
	if(clr='1') then
		seg(6 downto 0) <= "1111111";
	elsif(load='1') then
		seg(6 downto 0) <= "1111111";
	else	
		case(din) is 
			when x"0" => seg(6 downto 0) <= "1000000"; --to display 0
			when x"1" => seg(6 downto 0) <= "1111001"; --to display 1
			when x"2" => seg(6 downto 0) <= "0100100"; --to display 2
			when x"3" => seg(6 downto 0) <= "0110000"; --to display 3
			when x"4" => seg(6 downto 0) <= "0011001"; --to display 4
			when x"5" => seg(6 downto 0) <= "0010010"; --to display 5
			when x"6" => seg(6 downto 0) <= "0000011"; --to display 6
			when others => seg(6 downto 0) <= "1111111"; --blank
	  end case;
	 end if;
  end process;
END arch;
--------------------------------------------------------------------------------------------------------------------------------

-- top 
library ieee;
use ieee.std_logic_1164.all;
entity top is
port(osc, sw1, sw2,sw3,sw4: in std_logic;
		led: out std_logic_vector(3 downto 0);
		seg: out std_logic_vector(7 downto 0);
		an:    out std_logic_vector(7 downto 0)
		);
end entity;
architecture beh7 of top is
-- internal wire names for mapping
signal clk,clr,load:	std_logic;
signal d3,d4:	std_logic_vector(2 downto 0);

signal data_lfsr,out_lfsr: std_logic_vector(3 downto 0);
signal din: std_logic_vector(2 downto 0);
--signal led_ham,led_lfsr: std_logic_vector(6 downto 0);


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
			data_lfsr : in std_logic_vector(3 downto 0);
			out_lfsr : out std_logic_vector(3 downto 0));
end component;

component fsm
port(sw1,sw2,clk : IN std_logic;
		load,clr : OUT std_logic
	   );
end component;

component encoder
port(q: in std_logic_vector(4 downto 1);
		d3,d4: out std_logic_vector(2 downto 0));
end component;

component decoder
	port(sw3,sw4: in std_logic;
		d3,d4: in std_logic_vector(2 downto 0);
		din: out std_logic_vector(2 downto 0)
		);
end component;

component lcd
port(clk,clr, load : in std_logic;
	din: in std_logic_vector(2 downto 0);
	seg: out std_logic_vector(7 downto 0)
	);
end component;


-- begin making connections
begin
 g1: clkdiv port map(osc,clk);
 g2: fsm port map(sw1,sw2,clk,load,clr);
 g3: lfsr port map(clr,clk,data_lfsr);
 g4: dffs port map(load,clr,data_lfsr,out_lfsr);
 g5: encoder port map(out_lfsr,d3,d4);
 g6: decoder port map(sw3,sw4,d3,d4,din);
 g7: lcd port map (clk,clr,load,din,seg);
 
 led<=out_lfsr;
 an <= "11111110";
end beh7;




