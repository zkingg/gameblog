package com.core.util;

public class Pagination{
		public static final int ELEMENT_PAR_PAGE = 10;
		public int nb_page;
		public int current_page;
		public int tmp_page;
		public boolean has_next;
		public boolean has_prev;
		
		public Pagination(int nb_element,int current_page){
			this.nb_page=(int)(nb_element/ELEMENT_PAR_PAGE);
			if(nb_element % ELEMENT_PAR_PAGE != 0) this.nb_page++;
			
			this.current_page=current_page;
			has_prev = current_page > 1? true:false;
			has_next = current_page < nb_page? true:false;	
		}
	}