select cate_no,cate_name,
        prod_no,prod_name,prod_price,prod_detail
from product p join product_category pc 
on p.prod_cate=pc.cate_no
order by cate_no,prod_name;