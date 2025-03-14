# Amazon Corretto 21 + Alpine 3.21, multi-arch (amd64+arm64)
FROM amazoncorretto:21.0.6-alpine3.21

# 작업 디렉토리 설정
WORKDIR /app

#######################################################
# (1) 빌드 시점에 GitHub Actions로부터 전달받을 ARG들
#######################################################
ARG SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD
ARG JWT_SECRET
ARG KAKAO_CLIENT_ID
ARG KAKAO_REDIRECT_URI_PARTNER
ARG KAKAO_REDIRECT_URI_CLIENT

################################################################
# (2) ARG -> ENV 로 매핑 (컨테이너 런타임에 환경변수로 사용 가능)
################################################################
ENV SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL
ENV SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME
ENV SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD
ENV JWT_SECRET=$JWT_SECRET
ENV KAKAO_CLIENT_ID=$KAKAO_CLIENT_ID
ENV KAKAO_REDIRECT_URI_PARTNER=$KAKAO_REDIRECT_URI_PARTNER
ENV KAKAO_REDIRECT_URI_CLIENT=$KAKAO_REDIRECT_URI_CLIENT

##############################################
# (3) 빌드된 JAR 파일 복사 (파일명 주의)
##############################################
COPY build/libs/sentosa-spring-server-0.0.1-SNAPSHOT.jar /app/app.jar

##############################################
# (4) Spring Boot 실행
##############################################
ENTRYPOINT ["java","-Duser.timezone=Asia/Seoul","-Dspring.datasource.url=${SPRING_DATASOURCE_URL}","-Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME}","-Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}","-Djwt.secret=${JWT_SECRET}","-Dkakao.client.id=${KAKAO_CLIENT_ID}","-Dkakao.redirect.uri.partner=${KAKAO_REDIRECT_URI_PARTNER}","-Dkakao.redirect.uri.client=${KAKAO_REDIRECT_URI_CLIENT}","-jar","/app/app.jar"]
