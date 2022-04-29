library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
use ieee.std_logic_arith.all;
 


 entity top is
 
            	port (clk : in std_logic;
                                            	button : in std_logic_vector(4 downto 1);
                                            	y : out std_logic_vector(4 downto 1);
                                            	sf_ce0, lcd_e, lcd_rs, lcd_rw, lcd_7, lcd_6, lcd_5, lcd_4: out std_logic
                                            	);
 
end top;
 
architecture Behavioral of top is
 
component lfsr port (
                                            	reset, enable, clk : IN STD_LOGIC;
                                            	Q : out STD_LOGIC_VECTOR(3 downto 0)
                                            	);
end component;
 
component clkdiv port(
                            	CLK1: in std_logic;
                            	CLK2: out std_logic
                            	);
end component;
 
component demux4to2 port (
                            	Q : in std_logic_vector(4 downto 1);
                            	D3, D4 : out std_logic
                            	);
end component;
 
component mux4to1 port (
                            	sel : in std_logic_vector(1 downto 0);
                            	COMPARE, ADD, OR1, AND1: in std_logic_vector(3 downto 0);
                            	Y : out std_logic_vector(3 downto 0)
                            	);
end component;
 
component mux2to1 port (
                            	sel : in std_logic;
                            	LFSR, CALC: in std_logic_vector(3 downto 0);
                            	Y : out std_logic_vector(3 downto 0)
                            	);
end component;
 
component compartor port (
                            	d1, d2 : in std_logic;
                            	y : out std_logic
                            	);
end component;
 
component adder port (
 
                            	a, b : in std_logic;
                            	y : out std_logic_vector(1 downto 0)
                            	);
end component;
 
component lcd port(       	
                                            	f: in std_logic_vector(3 downto 0);
                                            	clk: in std_logic;
                                            	sf_ce0, lcd_e, lcd_rs, lcd_rw, lcd_7, lcd_6, lcd_5, lcd_4: out std_logic
                            	);
end component;
 
---------------- Signals
signal dout3, dout4, clk_Hz, compareOutput : std_logic;
 
signal muxSel : std_logic_vector(2 downto 1);
 
signal adderOutput : std_logic_vector(2 downto 1);
 
signal lfsr_output, F, output ,  muxCompare, muxOR, muxAND, muxADD : std_logic_vector(4 downto 1);
 
signal sf_ce00, lcd_e0, lcd_rs0, lcd_rw0, lcd_70, lcd_60, lcd_50, lcd_40: std_logic;
 
begin
 
            	muxSel <=(button(3) & button(4));
            	muxCompare <= "000" & compareOutput;
            	muxADD <= "00" & (adderOutput);
            	muxOR <= "000" & (dout4 or dout3 );
            	muxAND <= "000" & (dout4 and dout3 );
            	
            	
            	lcd1 : lcd port map (
            	
                                            	f => output,
                                            	clk => clk,
                                            	sf_ce0 => sf_ce00,
                                            	lcd_e => lcd_e0,
                                            	lcd_rs => lcd_rs0,
                                            	lcd_rw => lcd_rw0,
                                            	lcd_7 => lcd_70,
                                            	lcd_6 => lcd_60,
                                            	lcd_5 => lcd_50,
                                            	lcd_4 => lcd_40
                                            	);
            	
            	adder1: adder port map (
    
                                            	a => dout4,
                                            	b => dout3,
                                            	y => adderOutput
                                            	);
 
            	clkdiv1: clkdiv port map (
                                            	
                                            	CLK1 => clk,
                                            	CLK2 => clk_Hz
 
                            	);
                            	
            	lfsr1 : lfsr port map (
                                            	reset => button(1),
                                            	enable => button(2),
                                            	clk => clk_Hz,
                                            	Q => lfsr_output
                            	);
                            	
            	demux : demux4to2 port map (
            	
                                            	Q => lfsr_output,
                                            	D3 => dout3,
                                            	D4 => 	dout4
                            	);
                            	
            	comparator : compartor port map (
                            	            	d1 => dout4,
                                            	d2 => dout3,
                                            	y => compareOutput
            	
                            	);
            	mux_4 : mux4to1 port map (
            	
                                            	sel => muxSel,
                                            	COMPARE => muxCompare,
                                            	ADD => muxADD,
                                            	OR1 => muxOR,
                                            	AND1 => muxAND,
                                            	Y => F
                            	);
            	
            	mux_2 : mux2to1 port map (
                                            	sel => button(2),
                                            	LFSR => lfsr_output,
                                            	CALC => F,
                                            	Y => output
                            	);
 
y <= output;
 
sf_ce0 <= sf_ce00;
                                            	lcd_e <=  lcd_e0;
                                            	lcd_rs <=  lcd_rs0;
                                            	lcd_rw <=  lcd_rw0;
                                            	lcd_7 <=  lcd_70;
                                            	lcd_6 <=  lcd_60;
                                            	lcd_5 <=  lcd_50;
                                            	lcd_4 <=  lcd_40;
end Behavioral;
