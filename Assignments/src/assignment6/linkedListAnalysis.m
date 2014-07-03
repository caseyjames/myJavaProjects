%% lgVals 
% size = size*1000;
% time = time*1e9;
% avg = avg*1e3;C
%% smVals 
size = size/1000;
time = time/1e9;
avg = avg/1e6;
%% linked list timing.
format long
close, clc
clear N f

p = polyfit(size,time,2);
Y = polyval(p,size);
h = plot(size,time);
axis([0 100 0 5]);
set(h,'linestyle','-','Marker','.','MarkerSize',15,'LineWidth',1.5,'Color','b');
txt = text(50,4.95,'By: Casey Nordgran','HorizontalAlignment','center','VerticalAlignment','Top'...
        ,'FontSize',20,'FontName','AngsanaUPC','FontAngle','oblique','BackGround','w');

grid on
hold on
x = xlabel('Number of Calls ( x1000 )','FontSize',22,'FontName','Angsana New');
y = ylabel('Total Time ( seconds )','FontSize',25,'FontName','Angsana New');
t = title('Total Time for MyLinkedList to  get(i),  N  Number of Times'...
    ,'FontSize',26,'FontName','Agency FB','Color',[0 0 0.4]);
hFig = figure(1);
set(gcf,'PaperPositionMode','auto')
set(hFig, 'Position', [425 225 1100 650]);
g = plot(size,Y);
set(g,'linestyle','--','Marker','.','MarkerSize',14,'LineWidth',1.3,'Color','r');
legend('Actual Data','Fitted Line'); 
lh = legend('Location','NorthWest');
set(lh,'FontSize',12);



syms N f
f = poly2sym(p,N);
f = vpa(f,3);
eq = ['f = ',char(f)];

text(1.5,4,eq,'FontSize',17,'FontName','Agency FB','BackGround','w'...
    ,'BackGroundColor',[.9 .9 1])

%% linked list timing.
format long
close, clc
clear N f

p = polyfit(size,avg,1);
Y = polyval(p,size);
h = plot([0;size(2:end,:)],[0.002;avg(2:end,:)]);
axis([0 100 0 0.05])
set(h,'linestyle','-','Marker','.','MarkerSize',14,'LineWidth',1.5,'Color','b');
txt = text(50,0.0495,'By: Casey Nordgran','HorizontalAlignment','center','VerticalAlignment','Top'...
        ,'FontSize',22,'FontName','AngsanaUPC','FontAngle','oblique','BackGround','w');

grid on
hold on
x = xlabel('Number of Calls ( x1000 )','FontSize',22,'FontName','Angsana New');
y = ylabel('Average Time for  get(i) ( microseconds )','FontSize',24,'FontName','Angsana New');
t = title('Average Time for MyLinkedList to  get(i)  vs.  N Number of Times'...
    ,'FontSize',24,'FontName','Agency FB','Color',[0 0 0.4]);
hFig = figure(1);
set(gcf,'PaperPositionMode','auto')
set(hFig, 'Position', [425 225 1100 650]);
g = plot(size,Y);
set(g,'linestyle','--','Marker','.','MarkerSize',10,'LineWidth',1.35,'Color','r');
legend('Actual Data','Fitted Line'); 
lh = legend('Location','NorthWest');
set(lh,'FontSize',12);

syms N f
f = poly2sym(p,N);
f = vpa(f,3);
eq = ['f = ',char(f)];
text(1.5,0.04,eq,'FontSize',19,'FontName','Agency FB','BackGround','w'...
    ,'BackGroundColor',[.9 .9 1])

