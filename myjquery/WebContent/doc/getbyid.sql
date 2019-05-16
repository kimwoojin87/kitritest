select pc.cate_no,pc.cate_name,
       p.prod_no,p.prod_name,p.prod_price,p.prod_detail
from product p join product_category pc 
on p.prod_cate=pc.cate_no
where p.prod_no='002'
order by cate_no,prod_name;