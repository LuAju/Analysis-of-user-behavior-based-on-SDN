#!/bin/bash

##### 10M #####

	 ##Set enp5s0  20M
 	 ovs-vsctl set interface enp5s0 ingress_policing_rate=20000
	 ovs-vsctl set interface enp5s0 ingress_policing_burst=10
	 
	 ##Set enp5s1  10M
 	 ovs-vsctl set interface enp5s1 ingress_policing_rate=10000
	 ovs-vsctl set interface enp5s1 ingress_policing_burst=10
	 
	 ##Set enp5s2 
 	 ##ovs-vsctl set interface enp5s2 ingress_policing_rate=50000
	 ##ovs-vsctl set interface enp5s2 ingress_policing_burst=10


   
