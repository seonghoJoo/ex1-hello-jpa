# ex1-hello-jpa

## 프록시
1. 실제 클래스(원본 엔티티)를 상속받아서 만들어짐 Hibernate에서 내부적으로 만든것임 (== 비교 X  instance of 사용).
2. 사용하는 입장에선 진짜 객체인지 프록시 객체인지 구분하지 않고 사용함.
3. 프록시 객체는 실제 객체의 참조를 보관
4. 프록시 객체는 처음 사용할 때 한 번만 초기화
5. 프록시 객체를 초기화 할때, 프록시 객체가 실제 엔티티로 바뀌는 것이 아니라 초기화 되면 프록시 객체를 통해 실제 엔티티에 접근 가능
6. 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티를 반환

![image](https://user-images.githubusercontent.com/32606456/154500266-298b766a-4ab3-450b-9574-83a53aa05f0c.png)


### ```em.find()``` VS ```em.getReference()```
* ```em.find()``` : 데이터베이스를 통해서 실제 엔티티 객체 조회
* ```em.getReference()``` : 데이터베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
#### DB에 쿼리가 안나가는데 객체를 불러옴. (Hibernate가 만든 가짜 Proxy 객체 : Entity를 상속받음)
#### 하지만 DB에 있는 값을 진짜 가져올땐 DB에 쿼리를 날려 객체 얻어옴


## User(N) <-> Team(1) 
![image](https://user-images.githubusercontent.com/32606456/150733728-677af7dc-0758-43c9-9a7b-77b68420ad84.png)
### Fetch Join
```select m from Member m join fetch m.team t``` 1번에 조회
```select m from Member m join m.team t```은 N+1 조회

```
List<Member> result = em.createQuery(query, Member.class).getResultList();

for(Member m : result){
    System.out.println(m.getUsername() + " :  " + m.getTeam().getName() );
}
```

### 페이징 문제
페이징 처리를 하게 되면 데이터가 뻥튀기 한 값(객체)들을 가져오기 때문에 진정한 페이징 처리가 이뤄지지 않는다.
따라서 페이징을 하고 싶다면 Team 위주로 ```select t from Team t```로 불러와야 한다. 하지만 여기서 N+1의 문제가 발생한다.
이를 해결하기 위해 
```    
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
```
을 사용하면 된다. 운에 따라서 1+1문제로 해결이 된다. 여기서 1~10, 12, 25, 50 ,100 해서 총 14개로 Batchsize 날리는 쿼리를 최적화 할수 있다.
