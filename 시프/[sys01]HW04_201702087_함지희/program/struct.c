#include <stdio.h>

struct student{
	int sn;
	char* initial;
};

void swap(struct student* arg1, struct student* arg2){
	struct student temp;
	temp = *arg1;
	*arg1 = *arg2;
	*arg2 = temp;
}

int main(){
	struct student ham;
	struct student t;
	ham.sn = 201702087;
	ham.initial = "hjh";
	t.sn = 1;
	t.initial = "hdh";

	printf("[before] myname : %s, taname : %s \n", ham.initial, t.initial);
	printf("[before] mynum : %d, tanum : %d \n", ham.sn , t.sn);

	swap(&ham , &t);

	printf("[after] myname : %s, taname : %s \n", ham.initial, t.initial);
	printf("[after] mynum : %d, tanum : %d \n", ham.sn , t.sn);

	return 0;
}
