library ieee;
use ieee.std_logic_1164.all;

entity datapath is

	port(
	
		clock:  in std_logic;
		clr:  in std_logic;
		cin:  in std_logic;
		m:    in std_logic_vector(2 downto 0);
		w:    in std_logic_vector(2 downto 0);
		ce:   in std_logic_vector(3 downto 0);
		sel:  in std_logic_vector(1 downto 0);
		s:    in std_logic_vector(2 downto 0);
		dout: out std_logic
		
	);
	
end datapath;

architecture circuit of datapath is 

	--//////////////////////////////////////////////////////////
	
	component ffr
	port(
	
		clk:     in std_logic;
		ce:      in std_logic;
		reset:   in std_logic;
		d:       in std_logic;
		q:       out std_logic
		
	);
	end component;
	
	--//////////////////////////////////////////////////////////
	
	component mux2to1 
	port(
	
		d0:   in std_logic;
		d1:   in std_logic;
		s:    in std_logic;
		y:    out std_logic
		
	);
	end component;
	
	--//////////////////////////////////////////////////////////
	
	component mux4to1
	port(
	
		d0:   in std_logic;
		d1:   in std_logic;
		d2:   in std_logic;
		d3:   in std_logic;
		s:    in std_logic_vector(1 downto 0);
		y:    out std_logic
		
	);
	end component;
	
	--//////////////////////////////////////////////////////////
	
	component ALU 
	port(
	
		a:     in std_logic;
		b:     in std_logic;
		cin:   in std_logic;
		s:     in std_logic_vector(2 downto 0);
		y:     out std_logic
		
	);
	end component;
	
	--//////////////////////////////////////////////////////////


signal fin: std_logic;
signal m4:  std_logic;
signal y:   std_logic_vector(3 downto 0);
signal r:   std_logic_vector(2 downto 0);

begin

	dout <= r(2);

	g1: mux2to1 port map(m(0), fin, w(0), y(0));
	g2: mux2to1 port map(m(1), fin, w(1), y(1));
	g3: mux2to1 port map(m(2), fin, w(2), y(2));
	g4: ffr     port map(clock, ce(0), clr, y(0), r(0));
	g5: ffr     port map(clock, ce(1), clr, y(1), r(1));
	g6: ffr     port map(clock, ce(2), clr, y(2), r(2));
	g7: mux4to1 port map(r(0), r(1), r(2), '0', sel, m4);
	g8: ALU     port map(fin, m4, cin, s, y(3));
	g9: ffr     port map(clock, ce(3), clr, y(3), fin);

end circuit;