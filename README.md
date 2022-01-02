# ex1-hello-jpa

## 프록시

### ```em.find()``` VS ```em.getReference()```
* ```em.find()``` : 데이터베이스를 통해서 실제 엔티티 객체 조회
* ```em.getReference()``` : 데이터베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
#### DB에 쿼리가 안나가는데 객체를 
